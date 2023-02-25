function odliczanie(){
  var dzisiaj = new Date();

  var dzien = dzisiaj.getDate();
  var miesiac = dzisiaj.getMonth()+1;
  var rok = dzisiaj.getFullYear();
  var godzina = dzisiaj.getHours() < 10 ? "0"+dzisiaj.getHours() : dzisiaj.getHours()
  var minuty = dzisiaj.getMinutes() < 10 ? "0"+dzisiaj.getMinutes() : dzisiaj.getMinutes()
  var sekundy = dzisiaj.getSeconds() < 10 ? "0"+dzisiaj.getSeconds() : dzisiaj.getSeconds()


  document.getElementById("zegar").innerHTML =
    "\t"+godzina+":"+minuty+":"+sekundy;

  setTimeout("odliczanie()",1000)
}
