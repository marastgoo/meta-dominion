SUMMARY = "Out of tree build for the new cake qdisc"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://sch_cake.c;beginline=1;endline=36;md5=f7460465b8f00de680333c8d52c4447f"

# Made up version
PV = "2017.02"

SRCREV = "9789742cfc596d48583ba4cdbc8f38d026121fa6"
SRC_URI = "git://github.com/dtaht/sch_cake.git;protocol=https" 

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE += "KDIR=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/sched/
	install -m 0644 sch_cake.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/sched/
}
