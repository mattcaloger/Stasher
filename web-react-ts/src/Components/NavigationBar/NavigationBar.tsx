import React from 'react'
import styled from '@emotion/styled'
import { Link } from 'react-router-dom'
import { ReactComponent as Logo } from '../../media/logo2.svg'
import { Space } from '@mantine/core'

const NavContainer = styled.nav`
    width: 100%;
    height: 40px;
    padding: 10px;
    font-size: 24px;
    font-weight: bold;
    display:flex;
    align-items: center;
    justify-content: center;
    margin-top: 10px;
    margin-bottom: 10px;
`

const LogoContainer = styled.div`
    width: auto;
    height: 50px;
`

export default function NavigationBar() {
    return (
        <>
            <NavContainer>
                <Link to="/">
                    <LogoContainer>
                        <Logo />
                    </LogoContainer>
                </Link>
            </NavContainer>
            <Space h="lg" />
        </>
    
  )
}
