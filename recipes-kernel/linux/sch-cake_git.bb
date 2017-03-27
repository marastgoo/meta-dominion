SUMMARY = "Out of tree build for the new cake qdisc"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://sch_cake.c;beginline=1;endline=36;md5=e8cb01c6f6eed5ce11819e234bf9119e"

# Made up version
PV = "2017.03"

SRCREV = "e4a362875d2eaf2f35adf7f128ffaec5e2e846b7"
SRC_URI = "git://github.com/dtaht/sch_cake.git;protocol=https" 

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE += "KDIR=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/sched/
	install -m 0644 sch_cake.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/sched/
}
