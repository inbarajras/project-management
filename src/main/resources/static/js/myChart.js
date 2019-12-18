var htmlData = decodeHtml(chartData);
var chartDataArray = JSON.parse(htmlData);
var labelArray=[];
var valueArray=[];

for(var i=0;i<chartDataArray.length;i++){
	labelArray[i]=chartDataArray[i].label;
	valueArray[i]=chartDataArray[i].value;
}





new Chart(document.getElementById("myChart"), {
    type: 'doughnut',
    data: {
        labels: labelArray,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd","#8e5ea2","#3cba9F"],
            borderColor: 'rgb(255, 99, 132)',
            data:valueArray
        }]
    },

    // Configuration options go here
    options: {
    	title:{
    		display:true,
    		text:'Project Status'
    	}
    }
});

function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML=html;
	return txt.value;
}