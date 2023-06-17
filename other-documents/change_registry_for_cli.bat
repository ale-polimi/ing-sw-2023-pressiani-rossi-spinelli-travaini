@ECHO off
ECHO Changing Windows registry...
REG QUERY HKCU\Console /v VirtualTerminalLevel
IF %ERRORLEVEL% NEQ 0 (
	REG ADD HKCU\Console /v VirtualTerminalLevel /t REG_DWORD /d 1
	GOTO end
)
IF %ERRORLEVEL% EQU 0 (
	FOR /F "tokens=3" %%d IN ('reg query HKCU\Console /v VirtualTerminalLevel') DO (
		IF %%d EQU 0x0 (
			REG ADD HKCU\Console /v VirtualTerminalLevel /t REG_DWORD /d 1 /f
			ECHO Changed value.
			GOTO end
		)
		IF %%d EQU 0x1 (
			ECHO Value already present.
			GOTO end
		)
	)
)

:end
ECHO Windows registry updated.
