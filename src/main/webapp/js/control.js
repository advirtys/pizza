function isSelected(role) {

    var locale = navigator.language || navigator.userLanguage;

    if (role == 'guest') {
        if(locale == "ru") {
            alert("Если вы выберите эту роль то пользователь будет забанен!")
        } else {
            alert("If you select this role, then the user will be banned!")
        }
    }

}

function add() {
    window.location = "/do/add-product"
}