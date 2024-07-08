import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './components/form/form.component';
import { DetailComponent } from './components/detail/detail.component';
import { ListComponent } from './components/list/list.component';


const routes: Routes = [
  { title: 'Rentals', path: 'api/rentals/all', component: ListComponent },
  { title: 'Rentals - detail', path: 'api/rentals/detail/:id', component: DetailComponent },
  { title: 'Rentals - update', path: 'api/rentals/update/:id', component: FormComponent },
  { title: 'Rentals - create', path: 'api/rentals/create', component: FormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalRoutingModule { }
