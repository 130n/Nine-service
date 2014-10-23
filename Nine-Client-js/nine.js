
$('document').ready(function(){
    var fetchedWordlist = [];
    var letters='';
    var resultWords;
    $('#get-letters').click(function(){
        $.getJSON("http://localhost:8080/nine/getnine.json", function(data){
            $('p#field-letters').text(data.letters);
            letters = data.letters;
            enableSolve();
        });
    });
    $('#get-wordlist').click(function(){
        $('textarea#field-wordlist').text("loading...");
        $.getJSON("http://localhost:8080/nine/wordlist.json", function(data){
            console.log(data);
            fetchedWordlist = data.wordList;
            $('textarea#field-wordlist').text(data);
            enableSolve();
        });

    });

    $('#solve').click(function(){
        var facit = [];
        var mustLetter = letters.charAt(4);
        var allLetters = letters;
        console.log(facit);
        console.log(mustLetter);
        console.log(allLetters);

        for (var indexWL = 0; indexWL < fetchedWordlist.length ; indexWL++) {//loop over wordlist
            var word = fetchedWordlist[indexWL];
            console.log(word);
            if (word.length > 2 && word.indexOf(mustLetter) != -1) {
                var sb = allLetters;

                for (var i = 0; i < word.length; i++) {//loop over word

                    for (var e = 0; e < sb.length; e++) {//loop over the Nine per character
                        if (sb.charAt(e) == word.charAt(i)) {
                            sb = sb.slice(0,e)+sb.slice(e+1,sb.length);
                            break;
                        }
                    }
                }
                if (9 - sb.length == word.length) {
                    facit.push(word);
                }
            }
        }
        console.log(facit);
        $('textarea#field-solvelist').text(facit.join());
        resultWords = facit;
        enableVerify();
    });

    $('#verify').click(function(){
        var jsonData = {}
        jsonData['nineLetters'] = letters;
        jsonData['result'] = resultWords;

        $.ajax({
             url: "http://localhost:8080/nine/validate.json",
             type: "post",
             contentType: "application/json",
             accepts: "application/json",
             data: jsonData,
             //dataType : "json",
             success: function(data){
                 console.log(data);
             },
             error: function(jqXHR, textStatus, errorThrown) {
                alert("failure");
             }
        });
        /*
        $.post('http://localhost:8080/nine/validate',
            jsonData,
            headers: {
                             'Accept': 'application/json',
                             'Content-Type': 'application/json'
                         },
            function(data){
                console.log(data);
            }
        );
        */
    });

    function enableSolve(){
        console.log(letters);
        console.log(fetchedWordlist);
        if(letters != '' && typeof fetchedWordlist !== 'undefined' && fetchedWordlist.length > 0){
            $('#solve').removeAttr('disabled');
        }
    }

    function enableVerify(){
            console.log(letters);
            console.log(fetchedWordlist);
            if(letters != '' && typeof resultWords !== 'undefined' && resultWords.length > 0){
                $('#verify').removeAttr('disabled');
            }
        }
});


