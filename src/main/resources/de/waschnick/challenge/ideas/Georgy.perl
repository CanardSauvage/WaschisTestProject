@a=(1..100);

foreach $v (@a){
 ++$x % 3 == 0 and print "Fizz" , $v='';
 ++$y % 5 == 0 and print "Buzz" , $v='';
 print "$v\n";
}

for (1..100){
  ++$x % 3 or print "Fizz", $_='';
  ++$y % 5 or print "Buzz" , $_='';
 print "$_\n";
}

for(1..100){++$x%3or print"Fizz",$_='';++$y%5or print"Buzz",$_='';print"$_\n"}