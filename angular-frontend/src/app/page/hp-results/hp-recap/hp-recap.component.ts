import { Component, OnInit } from '@angular/core';
import { Chart } from 'angular-highcharts';

import { HealthPrintResultsService } from "../../../services/hp-results.service";
import { TranslateService } from "@ngx-translate/core";
import { HpConfigService } from "../../../services/hp-config.service";
import { AppService } from "../../../app.service";

@Component({
  selector: 'app-hp-recap',
  templateUrl: './hp-recap.component.html',
  styleUrls: ['./hp-recap.component.scss']
})
export class HpRecapComponent implements OnInit {
  isMobile = this.hpConfigService.isMobile();
  appConst = this.appService.getAppConst();
  lifeStyleRecap = [];
  dietRecap = [];
  pregnant = false;
  vegeterian = false;
  gluten = false;
  recap = {};
  kids = false;
  chart;
  isUS;

  constructor(private translate: TranslateService,
              private healthprintResultsService: HealthPrintResultsService,
              private appService: AppService,
              private hpConfigService: HpConfigService) { }

  ngOnInit() {
      this.healthprintResultsService.healthPrintResultInfo.subscribe( healthPrintResultInfo => {
          this.recap = healthPrintResultInfo['questions'];
          this.kids = (this.recap['age'] <= this.hpConfigService.getKitsAge())?true:false;
          if(this.kids === false){
              this.getRecap()
          }
      });
      this.isUS = this.appConst.country === "US";
  }

  getRecap(){
      let lifeStyleJson = this.hpConfigService.getLifeStyle();
      let dietJson = this.hpConfigService.getDiet();
      let dietChartJson = this.hpConfigService.getDietChart();
      let chartCategory = [];
      let chartIntake = [];
      let question, answer;
      let chartValue:number = null;

    /*
     * clearing data
     */
      this.lifeStyleRecap = [];
      this.dietRecap = [];
    
      if(this.recap['gender'] === "M")
      {
          lifeStyleJson.splice(7, 1);
      }

      lifeStyleJson.forEach( (value, key) => {
          question = value.replace("_", "-");

          if(question == 'pregnant'){
              answer = question+'-'+this.recap[value];
          }
          else {
              answer = question+this.recap[value];
          }
          this.lifeStyleRecap.push({ question: question, answer: answer });

          if(this.recap[value] == 'PREGNANT'){
              this.pregnant = true;
          }
      });

      dietJson.forEach((value, key) => {
          question = value.replace("_", "-");

          if(question == 'dietary-restrictions'){
              let options = [];
              if(this.recap[value] !== null) {
                  this.recap[value].forEach((value1, key1) => {
                      let val = question+'-'+key1;
                      options.push({diet: val});

                      if(key1 == 'VEGETERIAN'){
                          this.vegeterian = true;
                      }
                      if(key1 == 'GLUTEN'){
                          this.gluten = true;
                      }
                  });
              }

              // if user selected 'None' in the dietary restrictions, then set the answer to 'None'
              if(options.length === 0) {
                  options.push({diet: 'dietary-restrictions-NONE'});
              }

              answer = options;
          }
          else {
              answer = question+this.recap[value];
          }
         
          this.dietRecap.push({ question: question, answer: answer });
      });

      dietChartJson.forEach((value, key) => {
          chartCategory.push(value);
          if(this.recap[value] === 0){
              chartValue = 7;
          }else if(this.recap[value] === 1){
              chartValue = 14;
          }else if(this.recap[value] === 2){
              chartValue = 21;
          }else if(this.recap[value] === 3){
              chartValue = 28;
          }
          chartIntake.push(chartValue);
      });
      this.setChart(chartCategory, chartIntake);
  }

  setChart(chartCategory, chartIntake) {
      let title1, title2, fruits, veg, grains, dairy, weekTimes, lessThan7;
      this.translate.stream('chart.your-intake').subscribe((res) => {title1 = res});
      this.translate.stream('chart.rec-intake').subscribe((res) => {title2 = res});
      this.translate.stream('chart.fruits').subscribe((res)=>{fruits = res});
      this.translate.stream('chart.vegetables').subscribe((res)=>{veg = res});
      this.translate.stream('chart.grains').subscribe((res)=>{grains = res});
      this.translate.stream('chart.dairy').subscribe((res)=>{dairy = res});
      this.translate.stream('chart.times-week').subscribe((res)=>{weekTimes = res});
      this.translate.stream('chart.less-than-7').subscribe((res)=>{lessThan7 = res});

      this['chart'] = new Chart({
          chart: {
              type: 'bar',
              height: 280
          },
          tooltip: {
              enabled: false
          },
          credits: {
              enabled: false
          },
          legend: {
              verticalAlign: 'top',
              symbolWidth: 15,
              symbolRadius: 0,
              // squareSymbol: false,
              x: 10
          },
          plotOptions: {
              bar: {
                  states: {
                      hover: {
                          enabled: false
                      }
                  }
              }
          },
          series: [{
              name: title1,
              data: chartIntake,
              color: '#b6d89e'
          },{
              name: title2,
              data: [21,28,28,28],
              color: '#5d9732'
          }],
          title: {
              text: ''
          },
          // loading: false,
          xAxis: {
              categories:[fruits,veg, grains, dairy], //chartCategory,
              crosshair: true,
              tickLength: 0,
              labels: {
                  style: {fontSize: '12px'},
                  x: -3
              },
              lineColor: '#CCC'
          },
          yAxis: {
              title: {
                  text: weekTimes,
                  x: this.isMobile? -105: -200,
                  offset: 2,
                  style:{"color": "#000", "fontWeight": "bold", "fontSize": "12px" }
              },
              gridLineWidth: 0,
              lineWidth:1,
              plotLines: [{
                  color: '#EEE',
                  width: 1,
                  value: 0
              }],
              min: 0,
              max:28,
              endOnTick:true,
              tickInterval: 7,
              tickColor: '#CCC',
              tickLength: 5,
              tickWidth: 1,
              tickPosition: 'inside',
              lineColor: '#CCC',

              labels: {
                  formatter: function () {
                      let label = '';
                      if(this.value === 0){
                          label = '';
                      }else if(this.value === 7){
                          label = lessThan7;
                      }else if(this.value === 14){
                          label = '7-13';
                      }else if(this.value === 21){
                          label = '14-20';
                      }else if(this.value === 28){
                          label = '20+';
                      }
                      return label;
                  },
                  align: 'center',
                  style: {
                      fontSize: '13px'
                  }
              }
          }
          /*
            yAxis: {
                title: {
                    text: this.translate.get('chart.times-week'),
                    x: this.isMobile? -105: -215,
                    offset: 2,
                    style:{"color": "#000", "fontWeight": "bold", "fontSize": "12px" }
                },
                gridLineWidth: 0,
                lineWidth:1,
                plotLines: [{
                    color: '#EEE',
                    width: 1,
                    value: 0
                }],
                min: 0,
                max:28,
                endOnTick:true,
                tickInterval: 7,
                tickColor: '#CCC',
                tickLength: 5,
                tickWidth: 1,
                tickPosition: 'inside',
                lineColor: '#CCC',

                labels: {
                    formatter: function () {
                        let label = '';
                        if(this.value === 0){
                            label = '';
                        }else if(this.value === 7){
                            label = this.translate.get('chart.less-than-7');
                        }else if(this.value === 14){
                            label = '7-13';
                        }else if(this.value === 21){
                            label = '14-20';
                        }else if(this.value === 28){
                            label = '20+';
                        }
                        return label;
                    },
                    align: 'center',
                    style: {
                        fontSize: '10px'
                    }
                }
            }*/
      });
  }

  startOverQuiz(){
      this.healthprintResultsService.startOverQuiz();
  }

  openFreeMembershipInfo() {
      this.healthprintResultsService.openModal('freeMembershipInfo', '');
  }

  openFreeWipeDetails() {
      this.healthprintResultsService.openModal('FreeWipeDetails', '');
  }
}
