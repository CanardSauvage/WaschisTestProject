for i in range(1,101):print'FIZZBUZZ'if i%3<1 and i%5<1 else'FIZZ'if i%3<1 else'BUZZ'if i%5<1 else i


i=1;exec"print'FizzBuzz'[i%-3&4:12&8-i%5]or i;i+=1;"*100