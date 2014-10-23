Nine-service
============

Nine-service

    GET /nine/getnine.json

returns json: `{ letters: "examplexy" }`
(maybe a bit TOO random)


    GET /nine/wordlist

returns json: `{ wordList: ["a","bb",...] }`

    POST /nine/validate
    
data `{letters: "thelettrs", "result": ["words", "matching", "the", "nine"] }`

