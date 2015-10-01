$(document).on('ready', readyDocument);

function readyDocument()
{
    $.ajaxSetup({
        beforeSend: function () { disableForm(this); $('#loading').removeClass('oculto'); },
        complete: function () { enableForm(this); $('#loading').addClass('oculto'); },
        dataType: 'json',
        error: function () { alert("Error desconocido"); }
    });

    $('.ajax').on('submit', submitAjax);
    $('#sign-up').on('click', clickSignUp);
    $('#signinlink').on('click', clickSignIn);
}

function submitAjax(event)
{
    console.log('submit');
    
    var obj = 
            {
                nombres: $('#nombres').val(), 
                apellidos: $('#apellidos').val(), 
                email: $('#email').val(), 
                usuario: $('#usuario').val(), 
                contrasenha: $('#contrasenha').val()
            };
            
    var data = $(this).attr('action') === 'api/usuario' || $(this).attr('action') === 'api/editor' ? JSON.stringify(obj) : $(this).serialize();
    
    console.log(data);

    var conf =
            {
                context: $(this), 
                data: data,
                method: $(this).attr('method'),
                url: $(this).attr('action'),
                success: function (response)
                {
                    if (response.success)
                    {
                        window.location = response.url;
                    }
                    else
                    {
                        alert(response.message);
                    }
                }
            };
    
    if($(this).attr('action') === 'api/usuario' || $(this).attr('action') === 'api/editor')
    {
        conf.contentType = "application/json";
    }

    $.ajax(conf);

    event.preventDefault();
}

function clickSignUp(event)
{
    $('#loginbox').hide('slow');
    $('#signupbox').show('slow');
    $('#nombres').focus();
    event.preventDefault();
}

function clickSignIn(event)
{
    $('#signupbox').hide('slow');
    $('#loginbox').show('slow');
    $('#login-username').focus();
    event.preventDefault();
}