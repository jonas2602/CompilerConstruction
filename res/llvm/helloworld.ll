@str = constant [23 x i8] c"2 plus 3 ist gleich 5.\00"

define i32 @main() {
  call i32 @puts(i8* getelementptr inbounds ([23 x i8], [23 x i8]* @str, i64 0, i64 0))
  call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([23 x i8], [23 x i8]* @str, i64 0, i64 0))
  ret i32 10
}

declare i32 @printf(i8*, ...)
declare i32 @puts(i8* nocapture readonly)
declare i32 @putchar(i32)

define void @test() {
  ret void
}