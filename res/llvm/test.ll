; ModuleID = 'test.c'
source_filename = "test.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%struct.person = type { i8*, i32 }

@myval = dso_local global i32 5, align 4
@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%i\00", align 1
@arraytest.mystring = private unnamed_addr constant [5 x i8] c"abcde", align 1
@arraytest.mylist = private unnamed_addr constant [3 x i32] [i32 2, i32 4, i32 6], align 4
@.str.2 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.3 = private unnamed_addr constant [5 x i8] c"john\00", align 1
@.str.4 = private unnamed_addr constant [7 x i8] c"abc %f\00", align 1
@.str.5 = private unnamed_addr constant [3 x i8] c"%f\00", align 1
@.str.6 = private unnamed_addr constant [5 x i8] c"%c%f\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = call i8* @makeString(i8 signext 97, i8 signext 98)
  %2 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8* %1)
  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i32 0, i32 0), i32 1)
  ret i32 0
}

declare dso_local i32 @printf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @makeString(i8 signext, i8 signext) #0 {
  %3 = alloca i8, align 1
  %4 = alloca i8, align 1
  %5 = alloca i8*, align 8
  store i8 %0, i8* %3, align 1
  store i8 %1, i8* %4, align 1
  %6 = call noalias i8* @malloc(i64 3) #4
  store i8* %6, i8** %5, align 8
  %7 = load i8, i8* %3, align 1
  %8 = load i8*, i8** %5, align 8
  %9 = getelementptr inbounds i8, i8* %8, i64 0
  store i8 %7, i8* %9, align 1
  %10 = load i8, i8* %4, align 1
  %11 = load i8*, i8** %5, align 8
  %12 = getelementptr inbounds i8, i8* %11, i64 1
  store i8 %10, i8* %12, align 1
  %13 = load i8*, i8** %5, align 8
  %14 = getelementptr inbounds i8, i8* %13, i64 2
  store i8 0, i8* %14, align 1
  %15 = load i8*, i8** %5, align 8
  ret i8* %15
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @arraytest() #0 {
  %1 = alloca i8, align 1
  %2 = alloca [5 x i8], align 1
  %3 = alloca [3 x i32], align 4
  %4 = alloca i32, align 4
  %5 = alloca [12 x [12 x i8*]], align 16
  %6 = alloca i8*, align 8
  store i8 120, i8* %1, align 1
  %7 = bitcast [5 x i8]* %2 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 1 %7, i8* align 1 getelementptr inbounds ([5 x i8], [5 x i8]* @arraytest.mystring, i32 0, i32 0), i64 5, i1 false)
  %8 = load i8, i8* %1, align 1
  %9 = getelementptr inbounds [5 x i8], [5 x i8]* %2, i64 0, i64 3
  store i8 %8, i8* %9, align 1
  %10 = bitcast [3 x i32]* %3 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 4 %10, i8* align 4 bitcast ([3 x i32]* @arraytest.mylist to i8*), i64 12, i1 false)
  %11 = getelementptr inbounds [3 x i32], [3 x i32]* %3, i64 0, i64 1
  store i32 1000, i32* %11, align 4
  %12 = getelementptr inbounds [3 x i32], [3 x i32]* %3, i64 0, i64 1
  %13 = load i32, i32* %12, align 4
  store i32 %13, i32* %4, align 4
  %14 = getelementptr inbounds [12 x [12 x i8*]], [12 x [12 x i8*]]* %5, i64 0, i64 6
  %15 = getelementptr inbounds [12 x i8*], [12 x i8*]* %14, i64 0, i64 8
  %16 = load i8*, i8** %15, align 16
  store i8* %16, i8** %6, align 8
  %17 = getelementptr inbounds [5 x i8], [5 x i8]* %2, i64 0, i64 1
  %18 = load i8, i8* %17, align 1
  %19 = sext i8 %18 to i32
  %20 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 %19)
  ret void
}

; Function Attrs: argmemonly nounwind
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture writeonly, i8* nocapture readonly, i64, i1) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @pointertest() #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32**, align 8
  store i32 132, i32* %1, align 4
  store i32* %1, i32** %2, align 8
  %6 = load i32*, i32** %2, align 8
  %7 = load i32, i32* %6, align 4
  store i32 %7, i32* %3, align 4
  %8 = load i32, i32* %1, align 4
  store i32 %8, i32* %4, align 4
  store i32** %2, i32*** %5, align 8
  %9 = load i32, i32* %3, align 4
  %10 = add nsw i32 %9, 1
  store i32 %10, i32* %3, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @structtest() #0 {
  %1 = alloca %struct.person, align 8
  %2 = getelementptr inbounds %struct.person, %struct.person* %1, i32 0, i32 1
  store i32 12, i32* %2, align 8
  %3 = getelementptr inbounds %struct.person, %struct.person* %1, i32 0, i32 0
  store i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.3, i32 0, i32 0), i8** %3, align 8
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @mymod(i32, i32) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  store i32 %1, i32* %4, align 4
  %5 = load i32, i32* %3, align 4
  %6 = load i32, i32* %4, align 4
  %7 = srem i32 %5, %6
  ret i32 %7
}

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
  %8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7 x i8], [7 x i8]* @.str.4, i32 0, i32 0), double %7)
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
  %4 = load i32, i32* @myval, align 4
  %5 = sitofp i32 %4 to double
  %6 = fsub double 1.200000e+00, %5
  %7 = fptrunc double %6 to float
  store float %7, float* %3, align 4
  %8 = load float, float* %3, align 4
  %9 = fpext float %8 to double
  %10 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.5, i32 0, i32 0), double %9)
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
  %6 = sub nsw i32 %4, %5
  store i32 %6, i32* %3, align 4
  %7 = load i32, i32* %3, align 4
  ret i32 %7
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @testCast(double) #0 {
  %2 = alloca double, align 8
  store double %0, double* %2, align 8
  %3 = load double, double* %2, align 8
  %4 = fptosi double %3 to i8
  ret i8 %4
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @check(i32) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i8, align 1
  store i32 %0, i32* %2, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp ne i32 %4, 0
  br i1 %5, label %10, label %6

; <label>:6:                                      ; preds = %1
  %7 = load i32, i32* %2, align 4
  %8 = add nsw i32 %7, 3
  %9 = icmp ne i32 %8, 0
  br label %10

; <label>:10:                                     ; preds = %6, %1
  %11 = phi i1 [ true, %1 ], [ %9, %6 ]
  %12 = zext i1 %11 to i8
  store i8 %12, i8* %3, align 1
  ret i32 1
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
  %8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.6, i32 0, i32 0), i32 %5, double %7)
  ret void
}

; Function Attrs: nounwind
declare dso_local noalias i8* @malloc(i64) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @branches(i32) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = icmp sgt i32 %3, 0
  br i1 %4, label %5, label %13

; <label>:5:                                      ; preds = %1
  %6 = load i32, i32* %2, align 4
  %7 = icmp sgt i32 %6, 10
  br i1 %7, label %8, label %9

; <label>:8:                                      ; preds = %5
  store i32 999, i32* %2, align 4
  br label %10

; <label>:9:                                      ; preds = %5
  store i32 1, i32* %2, align 4
  br label %10

; <label>:10:                                     ; preds = %9, %8
  %11 = load i32, i32* %2, align 4
  %12 = add nsw i32 %11, 1
  store i32 %12, i32* %2, align 4
  br label %19

; <label>:13:                                     ; preds = %1
  %14 = load i32, i32* %2, align 4
  %15 = icmp eq i32 %14, 0
  br i1 %15, label %16, label %17

; <label>:16:                                     ; preds = %13
  store i32 0, i32* %2, align 4
  br label %18

; <label>:17:                                     ; preds = %13
  store i32 -1, i32* %2, align 4
  br label %18

; <label>:18:                                     ; preds = %17, %16
  br label %19

; <label>:19:                                     ; preds = %18, %10
  %20 = load i32, i32* %2, align 4
  ret i32 %20
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @loops(i32) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  store i32 0, i32* %3, align 4
  br label %4

; <label>:4:                                      ; preds = %7, %1
  %5 = load i32, i32* %3, align 4
  %6 = icmp slt i32 %5, 10
  br i1 %6, label %7, label %10

; <label>:7:                                      ; preds = %4
  %8 = load i32, i32* %3, align 4
  %9 = add nsw i32 %8, 1
  store i32 %9, i32* %3, align 4
  br label %4

; <label>:10:                                     ; preds = %4
  ret void
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { argmemonly nounwind }
attributes #3 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 7.0.0-3~ubuntu0.18.04.1 (tags/RELEASE_700/final)"}
