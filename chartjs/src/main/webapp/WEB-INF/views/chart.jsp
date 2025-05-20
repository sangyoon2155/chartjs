<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Chart.js 라이브러리 실습</h1>
	
	<!--  -->
	<div>
		<h1>1) 나라별 평균 나이</h1>
		<canvas id="chart1" style="width:100%;max-width:600px"></canvas>
	</div>
	<div>
		<h1>2) 성별 가입자 수</h1>
		<canvas id="chart2" style="width:100%;max-width:600px"></canvas>
	</div>
	<div>
		<h1>3) 년도별 나라별 가입자 수</h1>
		<canvas id="chart3" style="width:100%;max-width:600px"></canvas>
	</div>
	<div>
		<h1>4) 년도별 누적 가입자 수</h1>
		<canvas id="chart4" style="width:100%;max-width:600px"></canvas>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		// chart4
		$.ajax({
		    url : '/rest/totalCountByYear',
		    type : 'post',
		    success : function(data) {
		        const xyValues = [];
		
		        $(data).each(function(i, e){
		            // 각 점에 x(year), y(count) 값을 설정
		            xyValues.push({ x: e.year, y: e.count });
		        });	
		
		        new Chart("chart4", {
		            type: "scatter",
		            data: {
		                datasets: [{
		                    label: "연도별 총 가입자 수",
		                    pointRadius: 4,
		                    pointBackgroundColor: "rgb(0,0,255)",
		                    showLine: true,
		                    data: xyValues
		                }]
		            },
		            options: {
		                plugins: {
		                    legend: {
		                        display: false
		                    },
		                    title: {
		                        display: true,
		                        text: '연도별 가입자 수 추이'
		                    }
		                },
		                scales: {
		                    x: {
		                        type: 'linear',
		                        position: 'bottom',
		                        min: 2015,
		                        max: 2025,
		                        title: {
		                            display: true,
		                            text: '연도'
		                        }
		                    },
		                    y: {
		                        min: 0,
		                        max: 1000,
		                        title: {
		                            display: true,
		                            text: '가입자 수'
		                        }
		                    }
		                }
		            }
		        });
		    }
		});
	
	
		// chart3
		$.ajax({
			url : '/rest/countByYearAndCountry',
			type : 'post',
			success : function(data) {
				console.log(data);
				
				// start chart3
				// 2015, 2015, 2015, 2015, 2016 -> 2015, 2016
				const xValues = []; // 연도
				const d0 = []; // 독일
				const d1 = []; // 미국
				const d2 = []; // 한국
				const d3 = []; // 호주
				
				$(data).each(function(i, e){
					if(i%4==0) {
						xValues.push(e.year);
						d0.push(e.count);
					} else if(i%4==1){
						d1.push(e.count);
					} else if(i%4==2){
						d2.push(e.count);
					} else if(i%4==3){
						d3.push(e.count);
					}
				});
				
				console.log(xValues);
				
				new Chart("chart3", {
				  type: "line",
				  data: {
				    labels: xValues,
				    datasets: [{ 
					  label:'독일',
				      data: d0,
				      borderColor: "red",
				      fill: false
				    }, { 
				      label:'미국',
				      data: d1,
				      borderColor: "green",
				      fill: false
				    }, { 
				      label:'한국',
				      data: d2,
				      borderColor: "blue",
				      fill: false
				    }, { 
				      label:'호주',
				      data: d3,
				      borderColor: "yellow",
				      fill: false
					}]
				  },
				  options: {
				    legend: {display: true}
				  }
				});
				
				// endchart3
				
			}
		});
		// chart2
		$.ajax({
			url : '/rest/countByGender',
			type : 'post',
			success : function(data) {
				const xValues = [];
				const yValues = [];
				const barColors = ["skyblue", "pink"];
				
				$(data).each(function(i, e){
					xValues.push(e.gender);
					yValues.push(e.count);
				});
				
				new Chart("chart2", {
				  type: "doughnut",
				  data: {
				    labels: xValues,
				    datasets: [{
				      backgroundColor: barColors,
				      data: yValues
				    }]
				  },
				  options: {
				    title: {
				      display: true,
				      text: "성별 가입자 수"
				    }
				  }
				  
				});
			}
		});
		
		
		// chart1
		$.ajax({
			url : '/rest/avgAgeByCountry',
			type : 'post',
			success : function(data){
				// console.log(data);
				const xValues = []; // 나라
				const yValues = []; // 평균나이 
				const barColors = ["red", "blue", "yellow", "green"];
				
				$(data).each(function(i, e){
					xValues.push(e.country);
					yValues.push(e.age);
				});
				
				console.log(xValues);
				console.log(yValues);
				
				new Chart("chart1", {
				  type: "bar",
				  data: {
				    labels: xValues,
				    datasets: [{
				      backgroundColor: barColors,
				      data: yValues
				    }]
				  },
				  options: {
				    legend: {display: false},
				    scales: {
				      yAxes: [{
				        ticks: {
				          beginAtZero: true
				        }
				      }]
				    },
				
				    title: {
				      display: true,
				      text: "나라별 평균 나이"
				    }
				  }
				});
		
			}
		});
		
	</script>
</body>
</html>