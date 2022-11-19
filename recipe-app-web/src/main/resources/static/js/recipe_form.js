const $ = id => document.getElementById(id);
const log = whatever => console.log(whatever);

let description_input = $('recipe_description_input');
let description_mess = $('description_message');

let ingredient_input = $('');
let ingredient_mess = $('ingredient_message');

let directions_input = $('');
let directions_mess = $('directions_message');

const valid = () => {
    return `<div class="valid-feedback">Looks good!</div>`;
}
const invalid = (message="") => {
    return `<div id="validationServer04Feedback" class="invalid-feedback">${message}</div>`;
}