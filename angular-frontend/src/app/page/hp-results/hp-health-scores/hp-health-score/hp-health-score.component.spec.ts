import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HpHealthScoreComponent } from './hp-health-score.component';

describe('HpHealthScoreComponent', () => {
  let component: HpHealthScoreComponent;
  let fixture: ComponentFixture<HpHealthScoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HpHealthScoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HpHealthScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
