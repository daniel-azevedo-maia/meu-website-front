import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmConstrucaoComponent } from './em-construcao.component';

describe('EmConstrucaoComponent', () => {
  let component: EmConstrucaoComponent;
  let fixture: ComponentFixture<EmConstrucaoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmConstrucaoComponent]
    });
    fixture = TestBed.createComponent(EmConstrucaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
