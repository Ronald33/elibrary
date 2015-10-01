function disableForm($form)
{
    $form.find(':input').prop('disabled', true);
}

function enableForm($form)
{
    $form.find(':input').prop('disabled', false);
}

$(document).ready(function ()
{
    
});