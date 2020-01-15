import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceUpdateComponent } from './invoice-update.component';

describe('InvoiceUpdateComponent', () => {
  let component: InvoiceUpdateComponent;
  let fixture: ComponentFixture<InvoiceUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
