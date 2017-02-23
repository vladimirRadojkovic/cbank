
//show form onlyone on select
function showonlyone(thechosenone) {
    $('.izaberiAkciju').each(function () {
        if ($(this).attr("id") === thechosenone) {
            $(this).show(600);
        }
        else {
            $(this).hide(600);
        }
    });
}
//smooth show of page
function slowShow() {
    setTimeout((function () {
        $("body").css("display", "block");
    }), 500);
    this.login.username.focus();
}
function downShow() {
    setTimeout((function () {
        $("body").css("display", "block");
    }), 500);
}
//get context path
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
//onclick show selected user for applayng changes
function getData() {
    var choosen = $('#user');
    var array = [$('#ifirstName'), $('#ilastName'), $('#iemail'), $('#iphoneNo'),
        $('#ijmbg'), $('#iadresa'), $('#imesto'), $('#ioperater'), $('#irola')
    ];
    for (i = 0; i < array.length; i++) {
        array[i].val("");
    }
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: {name: choosen.val()},
        url: getContextPath() + '/podaci',
        success: function (data) {
            $('#ifirstName').val(data.ime);
            $('#ilastName').val(data.prezime);
            $('#iemail').val(data.email);
            $('#iphoneNo').val(data.brTelefona);
            $('#ijmbg').val(data.jmbg);
            $('#iadresa').val(data.adresa);
            $('#imesto').val(data.mesto);
            $('#ioperater').val(data.operater);
//            $('#irola').val(data.rola);
        }
    });
}
;
//get data for card changes******************************************************
function cardData() {
    var selected = $('#selected');
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: {name: selected.val()},
        url: getContextPath() + '/racuni',
        success: function (data) {
            document.getElementById('sviRacuni').innerHTML = '';
            var sel = document.getElementById('sviRacuni');
            for (i = 0; i < data.length; i++) {
                var opt = document.createElement("option");
                opt.innerHTML = data[i].brRacuna;
                opt.value = data[i].brRacuna;
                sel.appendChild(opt);
            }
        }
    });
}
;
//vhange card*******************************************************************
function changeCard() {
    var chooseTr = $('#sviRacuni');
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: {tr: chooseTr.val()},
        url: getContextPath() + '/promeni',
        success: function (data) {
            $('#izmeniStanje').val(data.saldo);
            $('#izmeniOgranicenje').val(data.ogranicenje);
            $('#rokovi').val(data.rokVazenja);
            $('#opisRacuna').val(data.opis);
            $('#nazivK').val(data.nazivKartice);
        }
    });
}
;
//****************************************************************************
function clearAll() {
    var podaci = [$('#izmeniStanje'), $('#izmeniOgranicenje'), $('#rokovi'),
        $('#sviRacuni'), $('#opisRacuna'), $('#nazivK')
    ];
    for (var i = 0; i < podaci.length; i++) {
        podaci[i].val('');
    }
}
//*******************************************************************************
function deleteCard() {
    var selected = $('#bKorisnik');
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: {name: selected.val()},
        url: getContextPath() + '/racuni',
        success: function (data) {
            document.getElementById('bKartica').innerHTML = '';
            var sel = document.getElementById('bKartica');
            for (i = 0; i < data.length; i++) {
                var opt = document.createElement("option");
                opt.innerHTML = data[i].brRacuna;
                opt.value = data[i].brRacuna;
                sel.appendChild(opt);
            }
        }
    });
}
;
//******************************************************************************
function checkUser() {
    var selected = $('#proveraKorisnika');
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: {name: selected.val()},
        url: getContextPath() + '/KorisnikProvera',
        success: function (data) {
            if (data === true) {
                $('#proveraKorisnika').css({"border-color": "red", "color": "red"});
                $('#unesiKorisnika').prop('disabled', true);
            } else {
                $('#unesiKorisnika').prop('disabled', false);
                $('#proveraKorisnika').css({"border-color": "", "color": ""});
            }
        }
    });
}
;
//******************************************************************************
function checkPassword() {
    var pass = $('#activatePassword').val();
    var repeatPass = $('#repeatActivatePassword').val();
    if (pass === repeatPass) {
        $('#serviceActivation').prop('disabled', false);
        $('#repeatActivatePassword').css({"border-color": "", "color": ""});
    } else {
        $('#serviceActivation').prop('disabled', true);
        $('#repeatActivatePassword').css({"border-color": "red", "color": "red"});
    }
}
//******************************************************************************
function loanInfo() {
    var choosen = $('#izabraniKredit');
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: {name: choosen.val()},
        url: getContextPath() + '/loans',
        success: function (data) {
            $('#godisnjaStopa').val(data.nominalnaGodisnjaStopa);
            $('#valutaKredita').val(data.valuta);
        }
    });
}
;
//******************************************************************************
function izracunajKredit() {
    var kamata = parseFloat($('#godisnjaStopa').val());
    var iznosKredita = parseInt($('#iznosKredita').val());
    var periodOtplate = parseInt($('#periodOtplate').val());

    $('#mesecniAnuitet').val(((iznosKredita + (iznosKredita / kamata)) / periodOtplate).toFixed(2));
    $('#iznosOtplate').val((iznosKredita + (iznosKredita / kamata)).toFixed(2));
    $('#iznosKamate').val((iznosKredita / kamata).toFixed(2));
    $('#kamata').val(kamata);

}
//********************************************************************************
$(function () {
    //----- OPEN
    $('[data-popup-open]').on('click', function (e) {
        var targeted_popup_class = jQuery(this).attr('data-popup-open');
        $('[data-popup="' + targeted_popup_class + '"]').fadeIn(350);

        e.preventDefault();
    });

    //----- CLOSE
    $('[data-popup-close]').on('click', function (e) {
        var targeted_popup_class = jQuery(this).attr('data-popup-close');
        $('[data-popup="' + targeted_popup_class + '"]').fadeOut(350);

        e.preventDefault();
    });
});
//******************************************************************************
function validateNumber(inputElement) {
    var inputText = inputElement.value;

    var regularExpression = /^[0-9]{1,15}$/;

    if (regularExpression.test(inputText)) {
        inputElement.style = "border-color: none; color: black;";

        return true;
    } else {
        inputElement.style = "border-color: red; color: red;";

        return false;
    }
}
//*****************************************************************************
function validateEmail(inputElement) {
    var inputText = inputElement.value;

    var regularExpression = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    if (regularExpression.test(inputText)) {
        inputElement.style = "border-color: none; color: black;";

        return true;
    } else {
        inputElement.style = "border-color: red; color: red;";

        return false;
    }
}
//*****************************************************************************
function validatePercent(inputElement) {
    var inputText = inputElement.value;

    var regularExpression = /^\d+(\.\d{1,2})?$/i;

    if (regularExpression.test(inputText)) {
        inputElement.style = "border-color: none; color: black;";

        return true;
    } else {
        inputElement.style = "border-color: red; color: red;";

        return false;
    }
}
//*****************************************************************************
function validateLength(inputElement, min, max) {
    var inputText = inputElement.value;

    if (inputText.length < min || inputText.length > max) {
        inputElement.value = "";
        inputElement.style = "border-color: red;";

        return false;
    } else {
        inputElement.style = "border-color: none;";

        return true;
    }
}
//******************************************************************************
function validateForm() {
    var iznos = document.forms["uplata"]["iznos"];
    if (!validateNumber(iznos)) {
        return false;
    }
    if (!validateLength(iznos, 2, 7)) {
        return false;
    }

    var nazivPrimaoca = document.forms["uplata"]["nazivPrimaoca"];
    if (!validateLength(nazivPrimaoca, 2, 20)) {
        return false;
    }

    var adresaPrimaoca = document.forms["uplata"]["adresaPrimaoca"];
    if (!validateLength(adresaPrimaoca, 1, 20)) {
        return false;
    }

    var mestoPrimaoca = document.forms["uplata"]["mestoPrimaoca"];
    if (!validateLength(mestoPrimaoca, 2, 20)) {
        return false;
    }

    var racunPrimaoca = document.forms["uplata"]["racunPrimaoca"];
    if (!validateLength(racunPrimaoca, 6, 10)) {
        return false;
    }
    if (!validateNumber(racunPrimaoca)) {
        return false;
    }

    var model = document.forms["uplata"]["model"];
    if (!validateLength(model, 2, 5)) {
        return false;
    }
    if (!validateNumber(model)) {
        return false;
    }

    var pozivNaBroj = document.forms["uplata"]["pozivNaBroj"];
    if (!validateLength(pozivNaBroj, 2, 20)) {
        return false;
    }

    var svrhaPlacanja = document.forms["uplata"]["svrhaPlacanja"];
    if (!validateLength(svrhaPlacanja, 5, 50)) {
        return false;
    }
    return true;
}
//****************************************************************************
function validateTForm() {
    var iznosTransfera = document.forms["transferForm"]["iznosTransfera"];
    if (!validateNumber(iznosTransfera)) {
        return false;
    }
    if (!validateLength(iznosTransfera, 2, 7)) {
        return false;
    }

    var racunPrimaoca = document.forms["transferForm"]["racunPrimaoca"];
    if (!validateNumber(racunPrimaoca)) {
        return false;
    }
    if (!validateLength(racunPrimaoca, 6, 10)) {
        return false;
    }
    return true;
}
//******************************************************************************
function validateUserEnter() {
    var ime = document.forms["enterNewUser"]["ime"];
    if (!validateLength(ime, 3, 20)) {
        return false;
    }

    var prezime = document.forms["enterNewUser"]["prezime"];
    if (!validateLength(prezime, 3, 20)) {
        return false;
    }

    var korisnickoIme = document.forms["enterNewUser"]["korisnickoIme"];
    if (!validateLength(korisnickoIme, 3, 20)) {
        return false;
    }

    var email = document.forms["enterNewUser"]["email"];
    if (!validateEmail(email)) {
        return false;
    }

    var jmbg = document.forms["enterNewUser"]["jmbg"];
    if (!validateNumber(jmbg)) {
        return false;
    }

    var brTelefona = document.forms["enterNewUser"]["brTelefona"];
    if (!validateNumber(brTelefona)) {
        return false;
    }

    var adresa = document.forms["enterNewUser"]["adresa"];
    if (!validateLength(adresa, 3, 50)) {
        return false;
    }

    var mesto = document.forms["enterNewUser"]["mesto"];
    if (!validateLength(mesto, 3, 20)) {
        return false;
    }
    var operater = document.forms["enterNewUser"]["operater"];
    if (!validateLength(operater, 3, 20)) {
        return false;
    }
    return true;
}
//******************************************************************************
function validateCardEnter() {
    var nazivKartice = document.forms["enterNewCard"]["nazivKartice"];
    if (!validateLength(nazivKartice, 3, 20)) {
        return false;
    }

    var ogranicenje = document.forms["enterNewCard"]["ogranicenje"];
    if (!validateNumber(ogranicenje)) {
        return false;
    }

    var opis = document.forms["enterNewCard"]["opis"];
    if (!validateLength(opis, 1, 20)) {
        return false;
    }

    var rokVazenja = document.forms["enterNewCard"]["rokVazenja"];
    if (!validateLength(rokVazenja, 3, 20)) {
        return false;
    }

    var saldo = document.forms["enterNewCard"]["saldo"];
    if (!validateNumber(saldo)) {
        return false;
    }

    var brRacuna = document.forms["enterNewCard"]["brRacuna"];
    if (!validateNumber(brRacuna)) {
        return false;
    }
    return true;
}
//******************************************************************************
function validateEditUser() {
    var ime = document.forms["editUserValid"]["ime"];
    if (!validateLength(ime, 3, 20)) {
        return false;
    }

    var prezime = document.forms["editUserValid"]["prezime"];
    if (!validateLength(prezime, 3, 20)) {
        return false;
    }

//    var korisnickoIme = document.forms["editUserValid"]["korisnickoIme"];
//    if (!validateLength(korisnickoIme, 3, 20)) {
//        return false;
//    }

    var email = document.forms["editUserValid"]["email"];
    if (!validateEmail(email)) {
        return false;
    }

    var jmbg = document.forms["editUserValid"]["jmbg"];
    if (!validateNumber(jmbg)) {
        return false;
    }

    var brTelefona = document.forms["editUserValid"]["brTelefona"];
    if (!validateNumber(brTelefona)) {
        return false;
    }

    var adresa = document.forms["editUserValid"]["adresa"];
    if (!validateLength(adresa, 3, 50)) {
        return false;
    }

    var mesto = document.forms["editUserValid"]["mesto"];
    if (!validateLength(mesto, 3, 20)) {
        return false;
    }
    var operater = document.forms["editUserValid"]["operater"];
    if (!validateLength(operater, 3, 20)) {
        return false;
    }
    return true;
}
//*******************************************************************************
function validateCardEdit() {
    var nazivKartice = document.forms["editCardValid"]["nazivKartice"];
    if (!validateLength(nazivKartice, 3, 20)) {
        return false;
    }

    var ogranicenje = document.forms["editCardValid"]["ogranicenje"];
    if (!validateNumber(ogranicenje)) {
        return false;
    }

    var opis = document.forms["editCardValid"]["opis"];
    if (!validateLength(opis, 1, 20)) {
        return false;
    }

    var rokVazenja = document.forms["editCardValid"]["rokVazenja"];
    if (!validateLength(rokVazenja, 3, 20)) {
        return false;
    }

    var saldo = document.forms["editCardValid"]["saldo"];
    if (!validateNumber(saldo)) {
        return false;
    }

//    var brRacuna = document.forms["editCardValid"]["brRacuna"];
//    if (!validateNumber(brRacuna)) {
//        return false;
//    }
    return true;
}
//*****************************************************************************
function validateLoanEnter() {
    var vrstaKredita = document.forms["enterLoanValid"]["vrstaKredita"];
    if (!validateLength(vrstaKredita, 3, 40)) {
        return false;
    }

    var nominalnaGodisnjaStopa = document.forms["enterLoanValid"]["nominalnaGodisnjaStopa"];
    if (!validatePercent(nominalnaGodisnjaStopa)) {
        return false;
    }

    var valuta = document.forms["enterLoanValid"]["valuta"];
    if (!validateLength(valuta, 3, 20)) {
        return false;
    }
    return true;
}