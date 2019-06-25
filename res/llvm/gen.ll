@.str.0 = constant [3 x i8] c"%d\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = add i32 2, 2
	%1 = add i32 15, %0
	call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.0, i64 0, i64 0), i32 %1)
	ret i32 0
}
