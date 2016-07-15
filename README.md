# labelpdf
Creates pdf labels according to a certain specification

##Build

```gradle build test```

##What is actually produced?

A pdf file that can be printed on consumer label printers. The current setup looks something like this:

![alt tag](https://raw.githubusercontent.com/brinxmat/labelpdf/master/labelexample.png)

The benefit of using a PDF like this is that we can reliably produce labels that incorporate pre-formatted barcodes and
unicode text for multiple scripts in ways that are not supported directly in PostScript and other PDLs.