@.str.0 = constant [3 x i8] c"%f\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = sitofp i32 1 to float
	%1 = fadd float 2.5, %0
	%2 = fpext float %1 to double
	call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.0, i64 0, i64 0), double %2)
	ret i32 0
}

