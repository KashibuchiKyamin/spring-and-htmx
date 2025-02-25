/**
 * 
 */
document.getElementById('inputCheck').addEventListener('click', function() {
	// Get the input and select elements inside the div
	const inputDiv = document.getElementById('input');
	const inputElements = inputDiv.querySelectorAll('div input, div select');

	//バリデーションをはさむとしたらここ

	cloneInputSelectToHiddenFields('input', 'send');

	document.getElementById('submitSearch').click();
});


function cloneInputSelectToHiddenFields(idNameOfFrom, idNameOfTo) {
	const inputDiv = document.getElementById(idNameOfFrom);
	const inputElements = inputDiv.querySelectorAll('input, select');

	const formElement = document.getElementById(idNameOfTo);

	while (formElement.firstChild) {
		formElement.removeChild(formElement.firstChild);
	}

	// Iterate over each input/select element
	inputElements.forEach(function(element) {
		// Create a hidden input element
		var hiddenInput = document.createElement('input');
		hiddenInput.type = 'hidden';
		hiddenInput.name = element.name;
		hiddenInput.value = element.value;

		// Append the hidden input to the form
		formElement.appendChild(hiddenInput);
	});

}