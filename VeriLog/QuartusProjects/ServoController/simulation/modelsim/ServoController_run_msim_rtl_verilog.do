transcript on
if {[file exists rtl_work]} {
	vdel -lib rtl_work -all
}
vlib rtl_work
vmap work rtl_work

vlog -vlog01compat -work work +incdir+D:/SeniorProject/Cloud-Car/VeriLog/QuartusProjects/ServoController {D:/SeniorProject/Cloud-Car/VeriLog/QuartusProjects/ServoController/pwm_generator.v}
vlog -vlog01compat -work work +incdir+D:/SeniorProject/Cloud-Car/VeriLog/QuartusProjects/ServoController {D:/SeniorProject/Cloud-Car/VeriLog/QuartusProjects/ServoController/clockDivider.v}

vlog -vlog01compat -work work +incdir+D:/SeniorProject/Cloud-Car/VeriLog/QuartusProjects/ServoController/simulation/modelsim {D:/SeniorProject/Cloud-Car/VeriLog/QuartusProjects/ServoController/simulation/modelsim/ServoController.vt}

vsim -t 1ps -L altera_ver -L lpm_ver -L sgate_ver -L altera_mf_ver -L altera_lnsim_ver -L cycloneive_ver -L rtl_work -L work -voptargs="+acc"  testBench

add wave *
view structure
view signals
run -all
