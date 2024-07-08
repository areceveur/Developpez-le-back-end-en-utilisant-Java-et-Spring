import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './components/form/form.component';
import { DetailComponent } from './components/detail/detail.component';
import { ListComponent } from './components/list/list.component';


const routes: Routes = [
  { title: 'Rentals', path: 'rentals/all', component: ListComponent },
  { title: 'Rentals - detail', path: 'rentals/detail/:id', component: DetailComponent },
  { title: 'Rentals - update', path: 'rentals/update/:id', component: FormComponent },
  { title: 'Rentals - create', path: 'rentals/create', component: FormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalRoutingModule { }
