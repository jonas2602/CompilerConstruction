@.str.0 = constant [3 x i8] c"%f\00"
@.str.1 = constant [3 x i8] c"%f\00"
@.str.2 = constant [3 x i8] c"%f\00"
@.str.3 = constant [3 x i8] c"%d\00"
@.str.4 = constant [3 x i8] c"%f\00"
@.str.5 = constant [3 x i8] c"%d\00"
@.str.6 = constant [3 x i8] c"%d\00"
@.str.7 = constant [3 x i8] c"%f\00"
@.str.8 = constant [3 x i8] c"%f\00"
@.str.9 = constant [3 x i8] c"%d\00"

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
	%13 = sitofp i32 2 to float
	%14 = fmul float 2.5, %13
	%15 = fpext float %14 to double
	%16 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.4, i64 0, i64 0), double %15)
	%17 = mul i32 2, 2
	%18 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.5, i64 0, i64 0), i32 %17)
	%19 = sdiv i32 2, 2
	%20 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.6, i64 0, i64 0), i32 %19)
	%21 = sitofp i32 2 to float
	%22 = fdiv float %21, 2.0
	%23 = fpext float %22 to double
	%24 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.7, i64 0, i64 0), double %23)
	%25 = sitofp i32 2 to float
	%26 = sitofp i32 2 to float
	%27 = fdiv float %25, %26
	%28 = fpext float %27 to double
	%29 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.8, i64 0, i64 0), double %28)
	%30 = srem i32 4, 2
	%31 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.9, i64 0, i64 0), i32 %30)
	ret i32 0
}

