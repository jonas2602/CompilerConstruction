; ModuleID = 'test.c'
source_filename = "test.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%struct.person = type { i8*, i32 }

@myval = dso_local global i32 0, align 4
@.str = private unnamed_addr constant [25 x i8] c"%s ist %d Jahre alt. %d\0A\00", align 1
@.str.1 = private unnamed_addr constant [7 x i8] c"abc %f\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%f\00", align 1
@.str.3 = private unnamed_addr constant [5 x i8] c"%c%f\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  %2 = alloca %struct.person, align 8
  call void @none()
  store i32 42, i32* %1, align 4
  %3 = getelementptr inbounds %struct.person, %struct.person* %2, i32 0, i32 0
  %4 = load i8*, i8** %3, align 8
  %5 = getelementptr inbounds %struct.person, %struct.person* %2, i32 0, i32 1
  %6 = load i32, i32* %5, align 8
  %7 = load i32, i32* %1, align 4
  %8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([25 x i8], [25 x i8]* @.str, i32 0, i32 0), i8* %4, i32 %6, i32 %7)
  ret i32 0
}

declare dso_local i32 @printf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @none() #0 {
  store i32 5, i32* @myval, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @test(i8 signext, i8 signext) #0 {
  %3 = alloca i8, align 1
  %4 = alloca i8, align 1
  store i8 %0, i8* %3, align 1
  store i8 %1, i8* %4, align 1
  %5 = load i8, i8* %3, align 1
  %6 = sitofp i8 %5 to float
  %7 = fpext float %6 to double
  %8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7 x i8], [7 x i8]* @.str.1, i32 0, i32 0), double %7)
  %9 = load i8, i8* %3, align 1
  %10 = sext i8 %9 to i32
  %11 = load i8, i8* %4, align 1
  %12 = sext i8 %11 to i32
  %13 = add nsw i32 %10, %12
  %14 = trunc i32 %13 to i8
  ret i8 %14
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local float @addf(float) #0 {
  %2 = alloca float, align 4
  %3 = alloca float, align 4
  store float %0, float* %2, align 4
  %4 = load float, float* %2, align 4
  %5 = load i32, i32* @myval, align 4
  %6 = sitofp i32 %5 to float
  %7 = fadd float %4, %6
  store float %7, float* %3, align 4
  %8 = load float, float* %3, align 4
  %9 = fpext float %8 to double
  %10 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), double %9)
  %11 = load float, float* %3, align 4
  ret float %11
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @addi(i32) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %4 = load i32, i32* %2, align 4
  %5 = load i32, i32* @myval, align 4
  %6 = add nsw i32 %4, %5
  store i32 %6, i32* %3, align 4
  %7 = load i32, i32* %3, align 4
  ret i32 %7
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @testCast(float) #0 {
  %2 = alloca float, align 4
  store float %0, float* %2, align 4
  %3 = load float, float* %2, align 4
  %4 = fptosi float %3 to i32
  ret i32 %4
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @check(i32) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  %4 = load i32, i32* %3, align 4
  %5 = icmp sgt i32 %4, 0
  br i1 %5, label %6, label %9

; <label>:6:                                      ; preds = %1
  %7 = load i32, i32* %3, align 4
  %8 = sdiv i32 %7, 2
  store i32 %8, i32* %2, align 4
  br label %13

; <label>:9:                                      ; preds = %1
  %10 = load i32, i32* %3, align 4
  %11 = sdiv i32 %10, 2
  %12 = add nsw i32 %11, 1
  store i32 %12, i32* %2, align 4
  br label %13

; <label>:13:                                     ; preds = %9, %6
  %14 = load i32, i32* %2, align 4
  ret i32 %14
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @myprint(i32, float) #0 {
  %3 = alloca i32, align 4
  %4 = alloca float, align 4
  store i32 %0, i32* %3, align 4
  store float %1, float* %4, align 4
  %5 = load i32, i32* %3, align 4
  %6 = load float, float* %4, align 4
  %7 = fpext float %6 to double
  %8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.3, i32 0, i32 0), i32 %5, double %7)
  ret void
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 7.0.0-3~ubuntu0.18.04.1 (tags/RELEASE_700/final)"}
