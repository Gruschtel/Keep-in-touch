import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { FollowerComponent } from './follower.component';

describe('FollowerComponent', () => {
  let component: FollowerComponent;
  let fixture: ComponentFixture<FollowerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FollowerComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(FollowerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
