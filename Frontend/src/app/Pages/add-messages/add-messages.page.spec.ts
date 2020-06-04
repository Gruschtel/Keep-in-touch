import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { AddMessagesPage } from './add-messages.page';

describe('AddMessagesPage', () => {
  let component: AddMessagesPage;
  let fixture: ComponentFixture<AddMessagesPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMessagesPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(AddMessagesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
