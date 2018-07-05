SUMMARY = "Linux Kernel 4.14.x"
DESCRIPTION = "Linux Kernel"
LICENSE = "GPL"
DEPENDS += ""

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.14.39.tar.xz"
SRC_URI += "file://0001-rt-preempt-patch-4.14.39-rt29.patch"
SRC_URI += "file://defconfig"
SRC_URI += "file://imx6dl-70041500-5.dts"
SRC_URI += "file://imx6dl-70041500-6.dts"
SRC_URI += "file://imx6dl-70041500-7.dts"
SRC_URI += "file://imx6q-70041500-0.dts"
SRC_URI += "file://imx6q-70041500-5.dts"
SRC_URI += "file://imx6q-70041500-6.dts"
SRC_URI += "file://imx6q-70041500-7.dts"
SRC_URI += "file://imx6qdl-cannon.dtsi"
SRC_URI += "file://SPL"
SRC_URI += "file://u-boot.img"

SYSROOT_DIRS = "/boot"

SRC_URI[md5sum] = "cbb2a17f13f12e4dfe1ed20a23d53ef4"
PATCHTOOL = "patch"

DEFAULT_PREFERENCE = "1"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SYSROOT_DIRS += "/boot"

do_configure_prepend () {
	install -m 0644 ${WORKDIR}/imx6dl-70041500-5.dts ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/
	install -m 0644 ${WORKDIR}/imx6dl-70041500-6.dts ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/
	install -m 0644 ${WORKDIR}/imx6dl-70041500-7.dts ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/
	install -m 0644 ${WORKDIR}/imx6q-70041500-0.dts ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/
	install -m 0644 ${WORKDIR}/imx6q-70041500-5.dts ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/
	install -m 0644 ${WORKDIR}/imx6q-70041500-6.dts ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/
	install -m 0644 ${WORKDIR}/imx6q-70041500-7.dts ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/

	install -m 0644 ${WORKDIR}/imx6qdl-cannon.dtsi ${WORKDIR}/linux-4.14.39/arch/arm/boot/dts/
}

FILES_${KERNEL_PACKAGE_NAME}-base += "/boot/*"

do_install_append () {
	install -d ${D}/boot/dtbs/4.14.39-rt27-cannon-automata
	mv ${D}/boot/zImage ${D}/boot/vmlinuz-4.14.39-rt27-cannon-automata
	rm ${D}/boot/devicetree-zImage-*.dtb
	mv ${D}/boot/*.dtb ${D}/boot/dtbs/4.14.39-rt27-cannon-automata/
	echo "uname_r=4.14.39-rt27-cannon-automata\ncmdline=rw consoleblank=0 fbcon=map:<012> fbcon=vc:0-2 imxdrm.legacyfb_depth=32" > ${D}/boot/uEnv.txt
}


do_deploy_append () {
	install -m 0644 ${WORKDIR}/SPL ${DEPLOYDIR}/
	install -m 0644 ${WORKDIR}/u-boot.img ${DEPLOYDIR}/
}

S = "${WORKDIR}/linux-4.14.39"
PR = "5"
PV = "4.14.39"

LINUX_VERSION ?= "4.14.39"
LINUX_VERSION_EXTENSION_append = "-ca-linux"

COMPATIBLE_MACHINE_imx6dl-k1 = "imx6dl-k1"
COMPATIBLE_MACHINE = "imx6dl-k1"
