import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


import { AdminLayoutRoutes } from './admin-layout.routing';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { TableComponent } from '../../pages/table/table.component';
import { ProductsComponent } from '../../pages/products/products.component';
import { CategoryComponent } from 'src/app/pages/category/category.component';
import { CreateCategoryComponent } from 'src/app/pages/create-category/create-category.component';
import { ProductCreateComponent } from 'src/app/pages/products/product-create/product-create.component';
import { CategoryUpdateComponent } from 'src/app/pages/category/category-update/category-update.component';
import { ProductUpdateComponent } from 'src/app/pages/products/product-update/product-update.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReportComponent } from 'src/app/pages/report/report.component';

import { NgxSpinnerModule } from 'ngx-spinner';







@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    NgbModule,
    NgxSpinnerModule
  ],
  declarations: [
    DashboardComponent,
    UserComponent,
    TableComponent,
    ProductsComponent,
    ProductCreateComponent,
    CategoryComponent,
    CreateCategoryComponent,
    ReportComponent,
    CategoryUpdateComponent,
    ProductUpdateComponent
  ]
})

export class AdminLayoutModule {}
