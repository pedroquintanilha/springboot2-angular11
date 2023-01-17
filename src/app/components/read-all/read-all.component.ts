import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwToolbarMixedModesError } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { Todo } from 'src/app/models/todo';
import { TodoService } from 'src/app/services/todo.service';
import { FinalizadosComponent } from '../finalizados/finalizados.component';

@Component({
  selector: 'app-read-all',
  templateUrl: './read-all.component.html',
  styleUrls: ['./read-all.component.css']
})
export class ReadAllComponent implements OnInit{

  closed = 0;

  list: Todo[] = [];
  listFinished: Todo[] = [];
  
  constructor(private service: TodoService, private router: Router) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe((resposta) => {
      resposta.forEach(todo => {
        if(todo.finalizado) {
          this.listFinished.push(todo);
        } else {
          this.list.push(todo);
        }
      })
      this.closed = this.listFinished.length
    })
  }

  done(item: Todo): void {
    item.finalizado = true;
    let data = new Date()
    item.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
    this.service.update(item).subscribe(() => {
      this.service.message('Task concluida com sucesso!');
      this.closed++;
      this.list = this.list.filter(todo => todo.id !== item.id);    
    })
  }

  delete(id: any): void {
    this.service.delete(id).subscribe((resposta) => {
      if(resposta === null) {
        this.service.message('Task deletada com sucesso!');
        this.list = this.list.filter(todo => todo.id !== id);
      }
    })
  }

  navegarParaFinalizados(): void {
    this.router.navigate(['finalizados'])
  }

}
