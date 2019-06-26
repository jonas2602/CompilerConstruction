@.str.0 = constant [3 x i8] c"%f\00"
@.str.1 = constant [3 x i8] c"%f\00"
@.str.2 = constant [3 x i8] c"%f\00"
@.str.3 = constant [3 x i8] c"%d\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = fsub float 2.5, 1.5
	%1 = fpext float %0 to double
	%2 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.0, i64 0, i64 0), double %1)
	%3 = sitofp i32 1 to float
	%4 = fsub float 2.5, %3
	%5 = fpext float %4 to double
	%6 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), double %5)
	%7 = sitofp i32 2 to float
	%8 = fsub float %7, 1.5
	%9 = fpext float %8 to double
	%10 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), double %9)
	%11 = sub i32 2, 1
	%12 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32 %11)
	ret i32 0
}

