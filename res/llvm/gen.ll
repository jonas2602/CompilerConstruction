@.str.0 = constant [6 x i8] c"Hello\00"
@.str.1 = constant [5 x i8] c"%s%d\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	call i32 @fact(i32 1)
	call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.1, i64 0, i64 0), i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.0, i64 0, i64 0), i32 15)
	ret i32 0
}
define i32 @fact(i32) {
	ret i32 0
}
