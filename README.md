# Minimal path

Given triangular graph like:

```mermaid
flowchart TD
    0[7] --> 1[6]
    0 --> 2[3]
    1 --> 4[3]
    1 --> 5[8]
    2 --> 5
    2 --> 6[5]
    4 --> 7[11]
    4 --> 8[2]
    5 --> 8
    5 --> 9[10]
    6 --> 9
    6 --> 10[9]
```

represented in a text like

```text
7
6 3
3 8 5
11 2 10 9
```

The program must compute a path with minimum sum of node weights from 
root to lives. Input are text lines from standard input
Expected output:

7 + 6 + 3 + 2 = 18

## Requirement

- Java 17 
- sbt

## Testing

sbt test

## Running 

```shell
$ sbt assembly
$ cat data/data4.txt | java -jar minimal_path.jar
7 + 6 + 3 + 2 = 18
$
```




