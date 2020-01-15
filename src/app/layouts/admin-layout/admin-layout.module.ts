import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxSpinnerModule } from 'ngx-spinner';


import { AdminLayoutRoutes } from './admin-layout.routing';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { TableComponent } from '../../pages/table/table.component';
import { ProductsComponent } from '../../pages/products/products.component';
import { CategoryComponent } from 'src/app/pages/category/category.component';
import { CreateCategoryComponent } from 'src/app/pages/category/create-category/create-category.component';
import { ProductCreateComponent } from 'src/app/pages/products/product-create/product-create.component';
import { CategoryUpdateComponent } from 'src/app/pages/category/category-update/category-update.component';
import { ProductUpdateComponent } from 'src/app/pages/products/product-update/product-update.component';
import { InvoiceComponent } from 'src/app/pages/invoice/invoice.component';
import { InvoiceUpdateComponent } from 'src/app/pages/invoice/invoice-update/invoice-update.component';
import { InvoiceCreateComponent } from 'src/app/pages/invoice/invoice-create/invoice-create.component';

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
    CategoryUpdateComponent,
    ProductUpdateComponent,
    InvoiceComponent,
    InvoiceUpdateComponent,
    InvoiceCreateComponent
  ]
})

export class AdminLayoutModule { }
