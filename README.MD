# Invoice Parser

This application can parse files, split data by specific property and export it in different formats. Client should pass
command-line arguments in order to specify input file, output directory, property to split data and output format.

## How to use it?

1.Build source code: `./gradlew build`

2.Find 'parser-1.0.jar' and place it somewhere in filesystem

3.Run `java -jar parser-1.0.jar absolutePathToInputFolder splitDataByThisProperty absolutePathToOutputDirectory outputFormat`

###Examples
#### Import `invoices.csv`, split by `buyer` and export data to `xml`
java -jar parser-1.0.jar /home/kolev/invoices/input/invoices.csv buyer /home/kolev/invoices/output xml

#### Import `invoices.csv`, split by `buyer` and export data to `csv`
java -jar parser-1.0.jar /home/kolev/invoices/input/invoices.csv buyer /home/kolev/invoices/output csv


- Otional
#### Import `invoices.csv`, split by `supplier` and export data to `xml`
java -jar parser-1.0.jar /home/kolev/invoices/input/invoices.csv supplier /home/kolev/invoices/output xml

#### Import `invoices.csv`, split by `supplier` and export data to `csv`
java -jar parser-1.0.jar /home/kolev/invoices/input/invoices.csv supplier /home/kolev/invoices/output csv

#### Import `invoices.csv`, split by `invoice_status` and export data to `xml`
java -jar parser-1.0.jar /home/kolev/invoices/input/invoices.csv invoice_status /home/kolev/invoices/output xml

#### Import `invoices.csv`, split by `invoice_status` and export data to `csv`
java -jar parser-1.0.jar /home/kolev/invoices/input/invoices.csv invoice_status /home/kolev/invoices/output csv