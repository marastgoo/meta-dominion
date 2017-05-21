require linux.inc

DEPENDS += "openssl-native"

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(rogue|dominion-old|beast|macbook|soekris-net6501|arietta-g25|macbook|minnow|minnowboard|fri2|beaglebone|apu2c4|revo)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-4.11:${FILE_DIRNAME}/linux-dominion-4.11/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "4.11.2"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-4.11.y"
SRCREV_pn-${PN} = "02d86837352952eb7ca4e9370fef944a16f69206"

SRC_URI += " \
             file://0001-wireless-populate-db.txt.patch \
             file://0001-bonding-sane-default-value-for-MAX_BONDS.patch \
             file://0002-block-cgroups-kconfig-build-bits-for-BFQ-v7r11-4.11..patch \
             file://0003-block-introduce-the-BFQ-v7r11-I-O-sched-for-4.11.0.patch \
             file://0004-block-bfq-add-Early-Queue-Merge-EQM-to-BFQ-v7r11-for.patch \
             file://0005-blk-bfq-turn-BFQ-v7r11-for-4.11.0-into-BFQ-v8r11-for.patch \
             file://am335x-pm-firmware.elf \
             file://defconfig \
             file://bbr.fragment \
             file://bfq.fragment \
             file://intel.fragment \
             file://block.fragment \
             file://btrfs.fragment \
             file://debug.fragment \
             file://systemd.fragment \
            "

KERNEL_CONFIG_FRAGMENTS_append = " \
                                  ${WORKDIR}/bbr.fragment \
                                  ${WORKDIR}/bfq.fragment \
                                  ${WORKDIR}/block.fragment \
                                  ${WORKDIR}/btrfs.fragment \
                                  ${WORKDIR}/debug.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_x86 = " \
                                  ${WORKDIR}/intel.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_x86_64 = " \
                                  ${WORKDIR}/intel.fragment \
                                 "

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.elf ] ; then
		cp ${WORKDIR}/am335x-pm-firmware.elf ${S}/firmware/
	fi
}
