var number = Math.floor(Math.random()*5)+1

var timer1 =0
var timer2 =0
var timer3 =0

function schowaj(){
  $("#slider").fadeOut(500)
}

function zmiensjadl(){
  // clearTimeout(timer3)
  number++;
  if(number>5){
    number =1
  }

  var plik = "<img src=\"slajdy/slajd"+number+".png\">"

  document.getElementById("slider").innerHTML = plik
  $("#slider").fadeIn(500);

  timer1 = setTimeout("zmiensjadl()", 5000)
  timer2 = setTimeout("schowaj()",4500)
}

function ustawslajd(n){
  clearTimeout(timer1)
  clearTimeout(timer2)
  number = n -1



  schowaj()
  clearTimeout(timer3)
  timer3 = setTimeout("zmiensjadl()",500)

}
