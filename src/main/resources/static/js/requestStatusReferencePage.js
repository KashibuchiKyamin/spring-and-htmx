/**
 * 
 */
document.getElementById('inputCheck').addEventListener('click', function() {
	// 入力血をすべて取得
	const inputDiv = document.getElementById('input');
	const inputElements = inputDiv.querySelectorAll('div input, div select');

	//バリデーションをはさむとしたらここ
	//	if(invalidInput(inputElements)){
	//		return;
	//	}

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

	// input/select タグをイテレートする。
	inputElements.forEach(function(element) {
		// 同じnemeのhiddenなinputを作る
		var hiddenInput = document.createElement('input');
		hiddenInput.type = 'hidden';
		hiddenInput.name = element.name;
		hiddenInput.value = element.value;

		// form配下にhiddenタグを追加
		formElement.appendChild(hiddenInput);
	});

}