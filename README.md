# labelpdf
Creates pdf labels according to a certain specification

##Build

```gradle build test```

##What is actually produced?

A pdf file that can be printed on consumer label printers. The current setup looks something like this:

![alt tag](https://raw.githubusercontent.com/brinxmat/labelpdf/master/labelexample.png)

The benefit of using a PDF like this is that we can reliably produce labels that incorporate pre-formatted barcodes and
unicode text for multiple scripts in ways that are not supported directly in PostScript and other PDLs.

##Usage

Creating labels:

```
    java -jar labelpdf-all-1.0-SNAPSHOT.jar --data='<formatted json>' --output='<filename.pdf>'
```

JSON should be formatted according to the following:

```
    {
         "callNumber": "820.000 Brims", 
         "creator": "Brims, Timo", 
         "title": "Å hello ЙЖ", 
         "publicationDate": "2014", 
         "holdingBranch": "HUTL", 
         "biblio": "3000321", 
         "copyNumber": "001",
         "barcode": "03011231231231"
    }
```

e.g.: 
```
    java -jar labelpdf-all-1.0-SNAPSHOT.jar \
        --data='{
                  "callNumber": "820.000 Brims",
                 "creator": "Brims, Timo",
                 "title": "Å hello ЙЖ",
                 "publicationDate": "2014",
                 "holdingBranch": "HUTL",
                 "biblio": "3000321",
                 "copyNumber": "001",
                 "barcode": "03011231231231"
                 }' \
                 --output="./file.pdf"
```