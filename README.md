# Scala program to calculate basket items

## Project Build
### Build Resource
* Scala 3.3.5
* JDK-17
* sbt 1.10

### Prequisite
* Install SBT with Homebrew or SDKMAN on Mac or use Chocolatey on a Windows machine.
``` choco install sbt ```.
Instructions on how to install can be found on https://www.scala-sbt.org/download
* The project was tested partly in Scala 2.13, but Scala 3 is preferred


### Instruction
1. Clone the repo to a local directory
```git clone https://github.com/Yomlat/glowing-guacamole.git```
2. ```cd glowing-guacamole```
3. Open your favourite command line in the directory
4. Type ``` sbt ``` in the command line to launch the sbt server
5. Type ``` compile ``` 
6. Once the code has finished compiling type ``` run PriceBasket.scala item1 item2 ``` where ```item1 ``` and ``` item2 ``` are the basket items. The program can accept abritrary number of items and return the price for the items in stock.

