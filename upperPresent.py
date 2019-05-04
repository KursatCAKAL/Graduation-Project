import present
import binascii
key = '10030508000103010000'.decode('hex')
evens = [20,40,60,80,20,40,60,80,20,40,60,80,20,40,60,80,20,40,60,80,20,40,60,80,20,40,60,80]
"""
for i in evens:
	plain = str(i).decode('hex') # decode rises error if number odd length you have to ensure even lenght or below method 
	binascii.hexlify(b'Hello')
	cipher = present.Present(key)
	encrypted = cipher.encrypt(plain)
	print(encrypted.encode('hex'))
"""

print(binascii.hexlify(b'Hello'))
testin='kc'
plain = binascii.hexlify(str(testin)).decode('hex')

p2=binascii.hexlify(str(plain)).encode('hex')
print(p2)
print(plain)

cipher = present.Present(key)
encrypted = cipher.encrypt(plain)
decrypted2 = cipher.decrypt(encrypted)
print str(str("dec    ")+str(decrypted2))
print(encrypted.encode('hex'))
#128 bit block cipher her karakter 16 bit 2byte ascii icinde

plain2 = binascii.hexlify(b'4')
print(plain2)
cipher2 = present.Present(key)
encrypted2 = cipher.encrypt(plain2)
print(encrypted2.encode('hex'))
