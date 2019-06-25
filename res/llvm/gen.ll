@.str.0 = constant [3 x i8] c"%d\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = call i32 @fact(i32 0)
	%1 = call i32 @fact(i32 %0)
	call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.0, i64 0, i64 0), i32 %1)
	ret i32 0
}

define i32 @fact(i32) {
	begin:
	%1 = add i32 1, %0
	ret i32 %1
}

