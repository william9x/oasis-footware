import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


import { AdminLayoutRoutes } from './admin-layout.routing';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { TableComponent } from '../../pages/table/table.component';
import { ProductsComponent } from '../../pages/products/products.component';
import { CreateProductComponent } from '../../pages/create-product/create-product.component';
import { CategoryComponent } from 'src/app/pages/category/category.component';
import { CreateCategoryComponent } from 'src/app/pages/create-category/create-category.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReportComponent } from 'src/app/pages/report/report.component';




@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    NgbModule
  ],
  declarations: [
    DashboardComponent,
    UserComponent,
    TableComponent,
    ProductsComponent,
    CreateProductComponent,
    CategoryComponent,
    CreateCategoryComponent,
    ReportComponent
  ]
})

export class AdminLayoutModule {}
