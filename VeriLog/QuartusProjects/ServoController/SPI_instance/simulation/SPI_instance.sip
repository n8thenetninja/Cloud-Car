set_global_assignment -entity "SPI_instance" -library "lib_SPI_instance" -name IP_TOOL_NAME "Qsys"
set_global_assignment -entity "SPI_instance" -library "lib_SPI_instance" -name IP_TOOL_VERSION "14.1"
set_global_assignment -entity "SPI_instance" -library "lib_SPI_instance" -name IP_TOOL_ENV "Qsys"
set_global_assignment -library "lib_SPI_instance" -name SPD_FILE [file join $::quartus(sip_path) "../SPI_instance.spd"]
set_global_assignment -library "lib_SPI_instance" -name MISC_FILE [file join $::quartus(sip_path) "../../SPI_instance.qsys"]

set_global_assignment -library "lib_SPI_instance" -name MISC_FILE [file join $::quartus(sip_path) "SPI_instance.v"]
set_global_assignment -library "lib_SPI_instance" -name MISC_FILE [file join $::quartus(sip_path) "submodules/spiphyslave.v"]
