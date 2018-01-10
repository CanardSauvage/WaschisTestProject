package de.waschnick.challenge.ideas

(1..100).collect{println it%15?it%3?it%5?it:'Buzz':'Fizz':'FizzBuzz'}