[![Maintainability](https://api.codeclimate.com/v1/badges/9d26cb4f5ad75618da98/maintainability)](https://codeclimate.com/github/JoelPintoMata/JavaSparkJSONReader/maintainability)  [![Test Coverage](https://api.codeclimate.com/v1/badges/9d26cb4f5ad75618da98/test_coverage)](https://codeclimate.com/github/JoelPintoMata/JavaSparkJSONReader/test_coverage)

# Java Spark JSON reader

## What is
A small application that simulates 7 different actors interested in receiving results according to one of the following rules
  0: only user "efteling"
  1: only socials containing: "disney"
  2: only socials not containing "disney"
  3: only socials with video content
  4: only socials created before 1st Feb 2017
  5: only socials created after 1st Feb 2017
  6: only Facebook socials

## Source structure
Array of:
{"socialType":"","socialId":"","timestamp":"","username":"","userId":"","content":"","latitude":N,"longitude":N}

## Running application
```
mvn spring-boot:run
```

## Running application tests
```
mvn test
```

#### Coverage
Current test class coverage of 100%
Current test method coverage of 95%