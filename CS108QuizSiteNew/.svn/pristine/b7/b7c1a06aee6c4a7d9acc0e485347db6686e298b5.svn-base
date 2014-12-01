/**
 * 
 */
var numMatches = 3;
var numAnswers = 2;
var numChecks = 2;
var numChoices = 2;

function reset() {
	document.getElementById("response").style.display = "none";
	document.getElementById("fill-blank").style.display = "none";
	document.getElementById("estimate").style.display = "none";
	document.getElementById("matching").style.display = "none";
	document.getElementById("multi-answer").style.display = "none";
	document.getElementById("multi-multi").style.display = "none";
	document.getElementById("multi-choice").style.display = "none";
	document.getElementById("math").style.display = "none";
	document.getElementById("picture").style.display = "none";
	
}

function showSelected() {
	reset();
	var selectedValue = document.getElementById("mySelect").value;
	document.getElementById(selectedValue).style.display = "block"
;}

function addMatch() {
	var lastRow = document.getElementById("match-"+numMatches);
	numMatches = numMatches + 1;
	
	var tableM = document.getElementById("matches");
    
	var newRow = document.createElement("TR");
	newRow.id = "match-" + numMatches;
    tableM.appendChild(newRow);

	var colLeft = document.createElement("TD");
	var colRight = document.createElement("TD");
	
	newRow.appendChild(colLeft);
	newRow.appendChild(colRight);
	
    var inputLeft = document.createElement("INPUT");
    inputLeft.type = "text";
    inputLeft.className = "form-control";
    inputLeft.placeholder = "Match " + numMatches;
 
    var inputRight = document.createElement("INPUT");
    inputRight.type = "text";
    inputRight.className = "form-control";
    inputRight.placeholder = "Match " + numMatches;
    
    colLeft.appendChild(inputLeft);
	colRight.appendChild(inputRight);
	
	newRow.appendChild(document.getElementById("more"));
}

function addAlternate(row, qtype) {
	var newCol = document.createElement("TD");
	
    var input = document.createElement("INPUT");
    input.type = "text";
    input.className = "form-control";
    input.id = "answer";
    input.placeholder = "Alternate Answer";
    
    newCol.appendChild(input);
    document.getElementById(qtype + "-answer-" + row).insertBefore(newCol, document.getElementById(qtype + "-alt-" + row));
    
}	
	
//<table id="multi-answers">
//<tr id="multi-answer-1">
//<td><input type="text" name="left-1" class="form-control" id="answer" placeholder="Answer 1"></td>
//<td id="multi-alt-1"><a float="right" href="javascript:addAlternate(1);">Add alternate answer</a></td>
//</tr>
//<tr id="multi-answer-2">
//<td><input type="text" name="left-2" class="form-control" id="answer" placeholder="Answer 2"></td>
//<td id="multi-alt-2"><a float="right" href="javascript:addAlternate(2);">Add alternate answer</a></td>
//</tr>				
//<tr id="multi-more">
//<td id="more"><a float="right" href="javascript:addAnswer();">Add another answer</a></td>
//</tr>
//</table>

function addAnswer() {
	numAnswers = numAnswers + 1;
	
	var tablet = document.getElementById("multi-answers");
	var lastrow2 = document.getElementById("multi-more");
	var newtr = document.createElement("TR");
	var newtd1 = document.createElement("TD");
	var newtd2 = document.createElement("TD");
	var newinput1 = document.createElement("input");
	var a = document.createElement("a");
	var linktext = document.createTextNode("Add alternate answer");
	
	newtr.id = "multi-answer-" + numAnswers;
	
	newinput1.type = "text";
	newinput1.name = "left-" + numAnswers;
	newinput1.className = "form-control";
	newinput1.id = "answer";
	newinput1.placeholder = "Answer " + numAnswers;
	
	newtd2.id = "multi-alt-" + numAnswers;
	a.appendChild(linktext);
	a.title = "Add alternate answer";
	a.href = "javascript:addAlternate(" + numAnswers+ ");";
	
	tablet.appendChild(newtr);	
	
	newtr.appendChild(newtd1);
	newtr.appendChild(newtd2);
	
	newtd1.appendChild(newinput1);
	newtd2.appendChild(a);
}

//<tr id="multi-option-2">
//<td class="check"><input type="checkbox" name="correct" value="option2"></td>
//<td><input type="text" name="left-2" class="form-control" id="answer" placeholder="Option 2"></td>
//	</tr>	
function addCheck(){
	numChecks = numChecks + 1;
	
	var newrow = document.createElement("TR");
	var checkcol = document.createElement("TD");
	var textcol = document.createElement("TD");
	var check = document.createElement("input");
	var text = document.createElement("input");
	
	newrow.id = "multi-option-" + numChoices;
	checkcol.className = "check";
	
	check.type = "checkbox";
	check.name = "correct";
	check.value = "option" + numChecks;
	
	text.type = "text";
	text.name = "left-" + numChecks;
	text.className = "form-control";
	text.id = "answer";
	text.placeholder = "Option " + numChecks;
	
	checkcol.appendChild(check);
	textcol.appendChild(text);
	newrow.appendChild(checkcol);
	newrow.appendChild(textcol);
	
	document.getElementById("multi-options").appendChild(newrow);
}

function addChoice(){
	numChoices = numChoices + 1;
	
	var newrow = document.createElement("TR");
	var radiocol = document.createElement("TD");
	var textcol = document.createElement("TD");
	var radio = document.createElement("input");
	var text = document.createElement("input");
	
	newrow.id = "multi-choice-" + numChoices;
	radiocol.className = "check";
	
	radio.type = "radio";
	radio.name = "correct";
	radio.value = "choice" + numChoices;
	
	text.type = "text";
	text.name = "left-" + numChoices;
	text.className = "form-control";
	text.id = "answer";
	text.placeholder = "Choice " + numChoices;
	
	radiocol.appendChild(radio);
	textcol.appendChild(text);
	newrow.appendChild(radiocol);
	newrow.appendChild(textcol);
	
	document.getElementById("multi-choices").appendChild(newrow);
}