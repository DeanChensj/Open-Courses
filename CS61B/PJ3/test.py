
def main():
  ans = 0.0
  for x in xrange(1,200):
    if x % 5 == 0:
      ans = ans + 5
      print x,
  ans = ans - 10

  ans = ans + 50 * 2

  print " lost " , ans 

  de = 0
  for x in xrange(1,200):
     if x % 11 == 0:
       de = de + 5
  de = de - 5
  print "back  ",  de

  print "finally spend" , ans - de

main()