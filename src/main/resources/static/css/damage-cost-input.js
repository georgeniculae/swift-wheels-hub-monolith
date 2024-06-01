function checkBox() {
     var isVehicleDamagedCheckbox = document.getElementById("isVehicleDamaged-id");
     var damageCostDiv = document.getElementById("damageCostDiv-id");

     if (isVehicleDamagedCheckbox.checked == true) {
          damageCostDiv.style.display="block"
     } else {
          damageCostDiv.style.display="none"
     }
}
